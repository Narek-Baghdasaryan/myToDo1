package com.example.mytodo.model;

import com.example.mytodo.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ToDo {

    private int id;
    private String title;
    private Date created_date;
    private Date finish_date;
    private User user;
    private Status status;
}
