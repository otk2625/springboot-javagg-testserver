package com.cos.javagg.model.post;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.cos.javagg.model.reply.Reply;
import com.cos.javagg.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@NoArgsConstructor
@Builder
@Data
@AllArgsConstructor
@Entity
public class Post {
	@Id //PK
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Table, auto_increment, Sequence
	private Integer id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob //대용량 데이터
	private String content;
	
	@ManyToOne()
	@JoinColumn(name = "userId")
	private User user;
	
	@ColumnDefault("0")
	private int likeCount;
	
	@ColumnDefault("0")
	private int viewCount;
	
	//양방향 매핑
	//cascade는 post를 없앨때 reply를 없앨건지 아닌지 설정! 댓글도 같이 삭제하도록 설계
	@OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({"post"}) //이건 무한매핑 방지(dto로 방지 가능 또는 이거)
	@OrderBy("id desc") 
	private List<Reply> replys;
	
	@CreationTimestamp
	private Timestamp createDate;
}


