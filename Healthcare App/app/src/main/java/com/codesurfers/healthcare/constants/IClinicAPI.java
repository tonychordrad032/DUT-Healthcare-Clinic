package com.codesurfers.healthcare.constants;

import com.codesurfers.healthcare.model.Appointment;
import com.codesurfers.healthcare.model.Clinic;
import com.codesurfers.healthcare.model.Feedback;
import com.codesurfers.healthcare.model.ResponseResult;
import com.codesurfers.healthcare.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IClinicAPI {
    // User
    @GET("user")
    Call<User> getUser(@Query("userId") long userId);

    @GET("user")
    Call<List<User>> getUsers();

    @POST("user")
    Call<User> addUser(@Body User user);

    @POST("user/login")
    Call<ResponseResult> loginUser(@Body User user);

    @PATCH("user/{id}")
    Call<User> updateUser(@Path("id") long userId, @Body User user);

    @DELETE("/user/{id}")
    Call<Void> deleteUser(@Path("id") long userId);

    // Clinic
    @GET("clinic")
    Call<Clinic> getClinic(@Query("clinicId") long clinicId);

    @GET("clinic")
    Call<List<Clinic>> getClinics();

    @POST("clinic")
    Call<Clinic> addClinic(@Body Clinic clinic);

    @PATCH("clinic/{id}")
    Call<Clinic> updateClinic(@Path("id") long clinicId, @Body Clinic clinic);

    @DELETE("/clinic/{id}")
    Call<Void> deleteClinic(@Path("id") long clinicId);

    // Feedback
    @GET("feedback")
    Call<Feedback> getFeedback(@Query("feedbackId") long feedbackId);

    @GET("feedback")
    Call<List<Feedback>> getFeedbacks();

    @POST("feedback")
    Call<Feedback> addFeedback(@Body Feedback feedback);

    @PATCH("feedback/{id}")
    Call<Feedback> updateFeedback(@Path("id") long feedbackId, @Body Feedback feedback);

    @DELETE("/feedback/{id}")
    Call<Void> deleteFeedback(@Path("id") long feedbackId);

    // Appointment
    @GET("appointment")
    Call<Appointment> getAppointment(@Query("appointmentId") long appointmentId);

    @GET("appointment")
    Call<List<Appointment>> getAppointments();

    @POST("appointment")
    Call<Appointment> makeAppointment(@Body Appointment appointment);

    @GET("appointment/findAppointmentByUserId")
    Call<ResponseResult> getAppointmentByUserId (@Query("userId") long userId);

    @PATCH("appointment/{id}")
    Call<Appointment> updateAppointment(@Path("id") long appointmentId, @Body Appointment appointment);

    @DELETE("/appointment/{id}")
    Call<Void> deleteAppointment(@Path("id") long appointmentId);
}