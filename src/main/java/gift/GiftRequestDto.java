package gift;

public record GiftRequestDto(
	Long id,
	String name,
	Long price,
	String imageUrl
) {
	//toEntity
	public Gift toEntity() {
		return Gift.of(id, name, price, imageUrl);
	}
}
