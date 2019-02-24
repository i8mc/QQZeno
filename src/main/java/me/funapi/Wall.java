package me.funapi;

public class Wall {
    private long QQ;
    private String nick;
    private String content;
    private boolean anonymous = false;

    public Wall(long QQ, String nick) {
        this.QQ = QQ;
        this.nick = nick;
    }

    public Wall(long QQ, String nick, String content) {
        this.QQ = QQ;
        this.nick = nick;
        this.content = content;
    }

    public Wall(long QQ, String nick, String content, boolean anonymous) {
        this.QQ = QQ;
        this.nick = nick;
        this.content = content;
        this.anonymous = anonymous;
    }

    public long getQQ() {
        return QQ;
    }

    public void setQQ(long QQ) {
        this.QQ = QQ;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }
}
