package com.taskmanagement.taskservice.exception;

import java.util.List;

public class UsersAlreadyAssignedToTask extends TaskServiceAlreadyException {
    public UsersAlreadyAssignedToTask(String message) {
        super(message);
    }

    public UsersAlreadyAssignedToTask(String message, String taskId, List<String> userIds) {
        super(message + " taskId: " + taskId + " userIds: " + userIds);
    }
}
