package cl.duocuc.ecomarket.modelo.mapper;

import cl.duocuc.ecomarket.modelo.dto.inventario.ProductoRequestDTO;
import cl.duocuc.ecomarket.modelo.dto.inventario.ProductoResponseDTO;
import cl.duocuc.ecomarket.modelo.entity.inventario.Producto;
import cl.duocuc.ecomarket.modelo.entity.inventario.Subfamilia;

public class ProductoMapper {

    public static ProductoResponseDTO toProductoResponseDTO(Producto p) {
        return new ProductoResponseDTO(
                p.getId(),
                p.getCodigoProducto(),
                p.getNombreProducto(),
                p.getDescripcion(),
                p.getPrecio(),
                p.getIdSubfamilia().getId(),
                p.getActivo(),
                p.getImg()
        );
    }

    public static Producto toEntity(ProductoRequestDTO dto) {
        Producto producto = new Producto();

        // Crear subfamilia usando solo el ID
        Subfamilia subfamilia = new Subfamilia();
        subfamilia.setId(dto.idSubFamilia());

        producto.setNombreProducto(dto.NombreProducto());
        producto.setCodigoProducto(dto.CodigoProducto());
        producto.setDescripcion(dto.Descripcion());
        producto.setPrecio(dto.Precio());
        producto.setIdSubfamilia(subfamilia);
        producto.setActivo(true);

        return producto;
    }

}
