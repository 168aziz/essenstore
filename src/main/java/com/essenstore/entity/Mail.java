package com.essenstore.entity;

import lombok.*;

import java.util.Map;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"to", "subject"})
public class Mail {

    private String to;

    private String subject;

    private Map<String, Object> model;

}
