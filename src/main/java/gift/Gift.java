package gift;

public class Gift {
	private Long id;
	private String name;
	private Long price;
	private String imageUrl;

	//of
	public static Gift of(Long id, String name, Long price, String imageUrl) {
		return new Gift(id, name, price, imageUrl);
	}

	private Gift(Long id, String name, Long price, String imageUrl) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.imageUrl = imageUrl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
