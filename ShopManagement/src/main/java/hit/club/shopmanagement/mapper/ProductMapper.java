package hit.club.shopmanagement.mapper;

//import hit.club.shopmanagement.dto.ProductDTO;
//import hit.club.shopmanagement.model.Product;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.Mappings;
//import org.mapstruct.Named;
//import org.springframework.web.multipart.MultipartFile;
//
//@Mapper(componentModel = "spring")
//public interface ProductMapper {
//    @Mappings({
//            @Mapping(target = "productName", source = "productDTO.productName"),
//            @Mapping(target = "count", source = "productDTO.count"),
//            @Mapping(target = "photo", source = "productDTO.photo", qualifiedByName = "convertMultipartFileToString"),
//            @Mapping(target = "description", source = "productDTO.description")
//    })
//    Product productDTOToProduct(ProductDTO productDTO);
//
//    @Named("convertMultipartFileToString")
//    default String convertMultipartFileToString(MultipartFile photo) {
//        return photo.getName();
//    }
//
//}
