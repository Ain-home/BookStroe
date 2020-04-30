package cn.entity;

public class Book {
	//书籍实体
	private String id;
    private String name;
    private String author;
    private String description;
    private double price;
    
    //书籍图片存放路径
    private String image;
    
    //记住分类的id(外键fk)
    private String category_id;

	public Book(String id, String name, String author, String description, double price, String image,
			String category_id) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.description = description;
		this.price = price;
		this.image = image;
		this.category_id = category_id;
	}

	public Book() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
}
