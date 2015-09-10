package org.yifan.interview.call;

import org.yifan.interview.employee.CallHandler;

import java.time.Duration;

/**
 * Call
 * @author Yifan Gu
 */
public class Call {

    public enum DifficultyLevel {
        EASY_LEVEL("easy"),
        MEDIUM_LEVEL("medium"),
        HARD_LEVEL("difficult");

        private final String level;

        DifficultyLevel(String level) {
            this.level = level;
        }

        String getDifficultyLevel() {
            return level;
        }
    }

    public final DifficultyLevel difficultyLevel;
    private CallHandler assignee;
    private long length;
    private long answeredTime;
    private boolean isOver = false;

    public Call(DifficultyLevel difficultyLevel, long length) {
        this.difficultyLevel = difficultyLevel;
        this.length = length;
    }

    public void setAssinee(CallHandler assignee) {
        this.assignee = assignee;
    }

    public CallHandler getAssignee(CallHandler assignee) {
        return assignee;
    }

    public void setAnsweredTime(CallHandler person) {
        answeredTime = new java.util.Date().getTime();
        setAssinee(person);
    }

    public boolean isEnded() {
        if ((new java.util.Date().getTime() - answeredTime)/1000 >= length) {
            isOver = true;
            assignee.finishCall();
        }
        return isOver;
    }
}
