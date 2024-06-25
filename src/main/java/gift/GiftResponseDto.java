package gift;

public record GiftResponseDto(
	Long id,
	String name,
	Long price,
	String imageUrl
) {
}
