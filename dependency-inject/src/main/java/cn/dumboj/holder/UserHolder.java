package cn.dumboj.holder;

import cn.dumboj.domain.User;

public class UserHolder {
    private User user;

    public UserHolder() {
    }
    /**
     * Support Annotation
     * */
    public UserHolder(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
