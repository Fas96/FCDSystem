package com.fas.fcdsystem.mockito.test_doubles.dummy;

public class DummyEmailService implements EmailService {
	@Override
	public void sendEmail(String message) {
		throw new AssertionError("Method not implemented !!!");
	}
}
