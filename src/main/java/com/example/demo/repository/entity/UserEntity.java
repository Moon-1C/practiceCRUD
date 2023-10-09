package com.example.demo.repository.entity;


import com.example.demo.repository.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

import static com.example.demo.repository.entity.UserEntity.USER_PREFIX;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@SuperBuilder(toBuilder = true)
@Entity(name = USER_PREFIX + "_entity")
@Table(name = USER_PREFIX + "_tb")
@SQLDelete(sql = "UPDATE member_tb SET deleted=true WHERE id = ?")
public class UserEntity extends BaseEntity {

    public static final String USER_PREFIX = "user";

    @Column(name = USER_PREFIX + "_name",nullable = false)
    private String name;

    @Column(name = USER_PREFIX + "_password",nullable = false)
    private String password;

    @Column(name = USER_PREFIX + "_email",nullable = false)
    private String email;


}
