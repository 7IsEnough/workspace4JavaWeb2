package com.clearlove.pojo;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author promise
 * @date 2023/3/1 - 22:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

  private String username;

  private String password;

  private String email;


}
