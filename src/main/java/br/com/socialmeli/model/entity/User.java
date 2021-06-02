package br.com.socialmeli.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_USERS")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "user name cannot be null")
	private String userName;

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<User> followed = new HashSet<>();

	@JsonIgnore
	@ManyToMany(mappedBy = "followed")
	private Set<User> followers = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Post> posts = new HashSet<>();

	private boolean isSeller;

	public void addFollowed(User user){
		this.followed.add(user);
	}

	public void removeFollowed(User user) { this.followed.remove(user);}

	public void addFollower(User user){
		this.followers.add(user);
	}

	public int getCountFollowers() {
		return this.followers.size();
	}

	public void addPost(Post post){
		this.posts.add(post);
	}
}
