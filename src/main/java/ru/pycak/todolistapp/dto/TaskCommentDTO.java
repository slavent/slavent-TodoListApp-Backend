package ru.pycak.todolistapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.pycak.todolistapp.model.TaskCommentModel;

import java.util.Date;

@Data
@RequiredArgsConstructor
public final class TaskCommentDTO {

    private final Long id;
    private final Long userId;
    private final Long taskId;
    private final String text;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private final Date creationDate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private final Date editDate;

    public TaskCommentDTO(TaskCommentModel model) {
        id = model.getId();
        userId = model.getUserId();
        taskId = model.getTaskId();
        text = model.getText();
        creationDate = model.getCreationDate();
        editDate = model.getEditDate();
    }
}
