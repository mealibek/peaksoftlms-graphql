package com.peaksoft.lms.services;

public interface EmailService {

  void sendEmail(String to, String subject, String body);
}
