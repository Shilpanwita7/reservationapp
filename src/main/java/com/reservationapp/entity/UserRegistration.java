package com.reservationapp.entity;


import javax.persistence.*;//@Entity @Table @Id, @GeneratedValue @Column
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user_registrations")
public class UserRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private  String name;
    private  String email;
    private  String password;

//    the datatype for the file uploading is byte array, @Lob for image uploading
//    profile picture datatype in the db will be tinyblob, if write byte & @Lob it converted to tinyblob
    @Lob
    @Column(name = "profile_picture", length = 1024)//by default length was 255 so tinyblob error while fetching
   private byte[] profilePicture;
}
