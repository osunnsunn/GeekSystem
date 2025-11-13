package com.example.demo.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class UsersForm {

	@NotNull(message = "役職を選択してください")
	private Integer rolesId;

	@NotNull(message = "権限を選択してください")
	private Integer permissionsId;

	@NotNull(message = "店舗を選択してください")
	private Integer storesId;

	@NotBlank(message = "姓を入力してください")
	private String firstName;

	@NotBlank(message = "名を入力してください")
	private String lastName;
	
	@NotNull(message = "年齢を入力してください")
	private Integer age;

	@NotBlank(message = "パスワードを入力してください")
	@Size(min = 4, message = "パスワードは４文字以上で入力してください")
	private String password;

	@NotBlank(message = "メールアドレスを入力してください")
	@Email(message = "正しいメールアドレスを入力してください")
	private String email;

	@NotBlank(message = "電話番号を入力してください")
	private String phone;

}