package net.novaborn.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.mail.internet.MimeMessage;

/**
 * A model representing a received email.
 * <p>
 * This object will be created and sent to observers by the {@code MailSaver} object.<br>
 * It contains useful data such as the content of the email and its path in the file system.
 * </p>
 *
 * @author Nilhcem
 * @since 1.0
 */
public final class EmailModel {
	private Date receivedDate;
	private String from;
	private List<String> to= new ArrayList<String>(
	);
	private String emailStr;
	private MimeMessage mineMessage;

	public Date getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}

	public void setTo(List<String> to) {
		this.to = to;
	}

	public List<String> getTo() {
		return to;
	}


	public String getEmailStr() {
		return emailStr;
	}

	public void setEmailStr(String emailStr) {
		this.emailStr = emailStr;
	}

	public MimeMessage getMineMessage() {
		return mineMessage;
	}

	public void setMineMessage(MimeMessage mineMessage) {
		this.mineMessage = mineMessage;
	}
}
