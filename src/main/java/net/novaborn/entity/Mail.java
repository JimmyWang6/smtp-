package net.novaborn.entity;

import java.io.Serializable;
import java.util.Date;

public class Mail implements Serializable {
    private Integer uid;

    private String messagId;

    private String userAddress;

    private String avatar;

    private String from;

    private String fromEmail;

    private String to;

    private String toEmail;

    private Date sendTime;

    private String subject;

    private Integer flag;

    private Integer deleted;

    private Integer draft;

    private Integer seen;

    private String mailBox;

    private Integer size;

    private String textContent;

    private String replyTo;

    private String data;

    private static final long serialVersionUID = 1L;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getMessagId() {
        return messagId;
    }

    public void setMessagId(String messagId) {
        this.messagId = messagId == null ? null : messagId.trim();
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress == null ? null : userAddress.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from == null ? null : from.trim();
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail == null ? null : fromEmail.trim();
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to == null ? null : to.trim();
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail == null ? null : toEmail.trim();
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Integer getDraft() {
        return draft;
    }

    public void setDraft(Integer draft) {
        this.draft = draft;
    }

    public Integer getSeen() {
        return seen;
    }

    public void setSeen(Integer seen) {
        this.seen = seen;
    }

    public String getMailBox() {
        return mailBox;
    }

    public void setMailBox(String mailBox) {
        this.mailBox = mailBox == null ? null : mailBox.trim();
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent == null ? null : textContent.trim();
    }

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo == null ? null : replyTo.trim();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data == null ? null : data.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", uid=").append(uid);
        sb.append(", messagId=").append(messagId);
        sb.append(", userAddress=").append(userAddress);
        sb.append(", avatar=").append(avatar);
        sb.append(", from=").append(from);
        sb.append(", fromEmail=").append(fromEmail);
        sb.append(", to=").append(to);
        sb.append(", toEmail=").append(toEmail);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", subject=").append(subject);
        sb.append(", flag=").append(flag);
        sb.append(", deleted=").append(deleted);
        sb.append(", draft=").append(draft);
        sb.append(", seen=").append(seen);
        sb.append(", mailBox=").append(mailBox);
        sb.append(", size=").append(size);
        sb.append(", textContent=").append(textContent);
        sb.append(", replyTo=").append(replyTo);
        sb.append(", data=").append(data);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}