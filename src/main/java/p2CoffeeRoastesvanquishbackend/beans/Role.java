package p2CoffeeRoastesvanquishbackend.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;



@Entity
@Table(name="role")

public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name="role_id")
	private int id;
	@Column(name="role_name")
	private String name;

	public Role() {
		id = 1;
		name = "User";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
