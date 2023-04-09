package com.codesurfers.duthealthcareclinic.feedback;

import com.codesurfers.duthealthcareclinic.utils.helpers.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedBackService {
    private static Logger LOG = LoggerFactory.getLogger(FeedBackService.class);

    @Autowired
    private FeedBackRepository feedBackRepository;


    /**
     * Saves province to Database using JPA
     * @param feedBack
     * @return
     */
    public ResponseEntity<ResponseResult> save(FeedBack feedBack, String correlationId) {
        try {
            LOG.info("{} : start adding feedBack process {}", correlationId, feedBack);
            if (feedBack == null || feedBack.getMessage().equals("")) {
                LOG.warn("{} : no content", correlationId);
                return ResponseEntity.noContent().build();
            }

            /**if(feedBackRepository.findByType(feedBack.getFeedBackType()) != null){
                LOG.warn("{} : feedBack already exists", correlationId);
                return ResponseEntity.status(409).body(new ResponseResult(409, "feedBack already exists", null));
            }*/

            feedBackRepository.save(feedBack);
            feedBackRepository.flush();
            LOG.info("{} : feedBack added successfully", correlationId);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(feedBack.getFeedbackId()).toUri();

            return ResponseEntity.created(location).body(new ResponseResult(201, "FeedBack was successfully added", null));
        } catch (Exception e) {
            LOG.error("{} : Error while adding feedBack {}", correlationId, e);
            return ResponseEntity.badRequest().body(new ResponseResult(400, e.getMessage(), null));
        }
        finally {
            LOG.info("{} : done adding feedBack process", correlationId);
        }
    }

    /**
     * Returns all the feedBacks
     * @return
     */
    public List<FeedBack> listAll() {

        return feedBackRepository.findAll()
                .stream()
                .filter(feedBack -> feedBack.getDeleted() == 0)
                .collect(Collectors.toList());
    }

    /**
     * update feedback
     * @return feedback
     */
    public ResponseEntity update(FeedBack feedBack, String correlationId){
        try {
            LOG.info("{} : Start updating feedback", correlationId);

            if (feedBack == null){
                LOG.warn("{} : No content", correlationId);
                return ResponseEntity.noContent().build();
            }
            FeedBack _feedBack = feedBackRepository.findById(feedBack.getFeedbackId()).orElseThrow();

            _feedBack.setName(feedBack.getName());
            _feedBack.setSurname(feedBack.getSurname());
            _feedBack.setFeedBackType(feedBack.getFeedBackType());
            _feedBack.setMessage(feedBack.getMessage());

            feedBackRepository.save(_feedBack);
            feedBackRepository.flush();

            LOG.info("{} :FeedBack was updated successfully", correlationId);

            return ResponseEntity.ok().body(new ResponseResult(200, "FeedBack was updated successfully", _feedBack));


        }catch (Exception e){
            LOG.error("{} : Error while updating feefback", correlationId, e);
            return ResponseEntity.badRequest().body(new ResponseResult(400, e.getMessage(), null));
        }finally {
            LOG.info("{} : Done updating feedback", correlationId);
        }
    }

    /**
     * delete feedBack
     */
    public ResponseEntity delete(long id, String correlationId){
        try {
            LOG.info("{} : Start deleting feedback", correlationId);
            FeedBack feedBack = feedBackRepository.findById(id).orElseThrow();

            if (feedBack.getDeleted() == 1){
                LOG.warn("{} : Not Found", correlationId);
                return ResponseEntity.status(404).body(new ResponseResult(404, "Feedback with id "+ feedBack.getFeedbackId()+ " is not found", null));
            }

            feedBack.setDeleted(1);
            feedBackRepository.save(feedBack);
            feedBackRepository.flush();

            LOG.info("{} : FeedBack was deleted successfully", correlationId);
            return ResponseEntity.ok().body(new ResponseResult(200, "FeedBack was deleted successfully", null));
        }catch (Exception e){
            LOG.error("{} : Error while deleting feedBack", correlationId, e);
            return ResponseEntity.badRequest().body(new ResponseResult(400, e.getMessage(), null));
        }finally {
            LOG.info("{} : Done deleting feedBack", correlationId);
        }
    }

}
