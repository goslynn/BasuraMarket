package cl.duocuc.ecomarket.servicio;


import cl.duocuc.ecomarket.modelo.dto.inventario.BodegaRequestDTO;
import cl.duocuc.ecomarket.modelo.dto.inventario.BodegaResponseDTO;
import cl.duocuc.ecomarket.modelo.dto.inventario.FamiliaRequestDTO;
import cl.duocuc.ecomarket.modelo.dto.inventario.FamiliaResponseDTO;
import cl.duocuc.ecomarket.modelo.dto.inventario.InventarioRequestDTO;
import cl.duocuc.ecomarket.modelo.dto.inventario.InventarioResponseDTO;
import cl.duocuc.ecomarket.modelo.dto.inventario.ProductoRequestDTO;
import cl.duocuc.ecomarket.modelo.dto.inventario.ProductoResponseDTO;
import cl.duocuc.ecomarket.modelo.dto.inventario.SubFamiliaRequestDTO;
import cl.duocuc.ecomarket.modelo.dto.inventario.SubFamiliaResponseDTO;
import cl.duocuc.ecomarket.modelo.dto.inventario.SucursalRequestDTO;
import cl.duocuc.ecomarket.modelo.dto.inventario.SucursalResponseDTO;
import cl.duocuc.ecomarket.modelo.entity.inventario.*;
import cl.duocuc.ecomarket.modelo.mapper.BodegaMapper;
import cl.duocuc.ecomarket.modelo.mapper.FamiliaMapper;
import cl.duocuc.ecomarket.modelo.mapper.InventarioMapper;
import cl.duocuc.ecomarket.modelo.mapper.ProductoMapper;
import cl.duocuc.ecomarket.modelo.mapper.SubFamiliaMapper;
import cl.duocuc.ecomarket.modelo.mapper.SucursalMapper;
import cl.duocuc.ecomarket.modelo.repository.BodegaRepository;
import cl.duocuc.ecomarket.modelo.repository.FamiliaRepository;
import cl.duocuc.ecomarket.modelo.repository.InventarioRepository;
import cl.duocuc.ecomarket.modelo.repository.ProductoRepository;
import cl.duocuc.ecomarket.modelo.repository.SubFamiliaRepository;
import cl.duocuc.ecomarket.modelo.repository.SucursalRepository;
import cl.duocuc.ecomarket.util.exception.ApiException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServicioInventario {

    private final InventarioRepository inventarioRepo;
    private final BodegaRepository bodegaRepo;
    private final SucursalRepository sucursalRepo;
    private final FamiliaRepository familiaRepo;
    private final SubFamiliaRepository subFamiliaRepo;
    private final ProductoRepository productoRepo;
    //private final  bodegaRepo;

    public ServicioInventario(InventarioRepository inventarioRepo, BodegaRepository bodegaRepo, SucursalRepository sucursalRepo, FamiliaRepository familiaRepo, SubFamiliaRepository subFamiliaRepo, ProductoRepository productoRepo) {
        this.inventarioRepo = inventarioRepo;
        this.bodegaRepo = bodegaRepo;
        this.sucursalRepo = sucursalRepo;
        this.familiaRepo = familiaRepo;
        this.subFamiliaRepo = subFamiliaRepo;
        this.productoRepo = productoRepo;
    }


    // ==============================
    // Métodos para Inventario
    // ==============================

    public InventarioResponseDTO obtenerInventario(Long id) throws ApiException {
        return inventarioRepo.findById(id)
                .filter(Inventario::getActivo)
                .map(InventarioMapper::toResponseDTO)
                .orElseThrow(() -> new ApiException(404, String.format("El inventario con ID %d no existe", id)));
    }


    public InventarioResponseDTO crearInventario(InventarioRequestDTO dto) {
        Inventario nuevoInventario = InventarioMapper.toEntity(dto);
        Inventario guardado = inventarioRepo.save(nuevoInventario);
        return InventarioMapper.toResponseDTO(guardado);
    }

    public void actualizarInventario(Long id, InventarioRequestDTO dto) throws ApiException {
        Inventario existente = inventarioRepo.findById(id)
                .filter(Inventario::getActivo)
                .orElseThrow(() -> new ApiException(404, String.format("El inventario con ID %d no existe", id)));

        inventarioRepo.save(existente);
    }

    public void desactivarInventario(Long id) throws ApiException {
        Inventario existente = inventarioRepo.findById(id)
                .filter(Inventario::getActivo)
                .orElseThrow(() -> new ApiException(404, String.format("El inventario con ID %d no existe", id)));

        // Cambia el estado a inactivo
        existente.setActivo(false);
        inventarioRepo.save(existente);
    }

         // ==============================
        // Métodos para Bodega
        // ==============================

    public BodegaResponseDTO obtenerBodega(Long id) throws ApiException {
        return bodegaRepo.findById(id)
                .filter(Bodega::getActiva)
                .map(BodegaMapper::toResponseDTO)
                .orElseThrow(() -> new ApiException(404, String.format("La bodega con ID %d no existe", id)));
    }

    public BodegaResponseDTO crearBodega(BodegaRequestDTO dto) {
        Bodega nuevaBodega = BodegaMapper.toEntity(dto);
        Bodega guardada = bodegaRepo.save(nuevaBodega);
        return BodegaMapper.toResponseDTO(guardada);
    }

    public void actualizarBodega(Long id, BodegaRequestDTO dto) throws ApiException {
        Bodega existente = bodegaRepo.findById(id)
                .filter(Bodega::getActiva)
                .orElseThrow(() -> new ApiException(404, String.format("La bodega con ID %d no existe", id)));

        // Verifica que la sucursal exista
        Sucursal sucursal = sucursalRepo.findById(dto.idSucursal())
                .orElseThrow(() -> new ApiException(404, String.format("La sucursal con ID %d no existe", dto.idSucursal())));

        // Actualiza los datos de la bodega
        existente.setNombreBodega(dto.nombreBodega());
        existente.setIdSucursal(sucursal);

        bodegaRepo.save(existente);
    }


    public void desactivarBodega(Long id) throws ApiException {
        Bodega existente = bodegaRepo.findById(id)
                .filter(Bodega::getActiva)
                .orElseThrow(() -> new ApiException(404, String.format("La bodega con ID %d no existe", id)));
        existente.setActiva(false);
        bodegaRepo.save(existente);
    }

    public List<BodegaResponseDTO> listarBodegas() throws ApiException {
        return bodegaRepo.findAll().stream()
                .filter(Bodega::getActiva)
                .map(BodegaMapper::toResponseDTO)
                .toList();
    }


    // ==============================
    // Métodos para Sucursal
    // ==============================

    public SucursalResponseDTO obtenerSucursal(Long id) throws ApiException {
        return sucursalRepo.findById(id)
                .filter(Sucursal::getActivo)
                .map(SucursalMapper::toResponseDTO)
                .orElseThrow(() -> new ApiException(404, String.format("La sucursal con ID %d no existe", id)));
    }

    @Transactional
    public SucursalResponseDTO crearSucursal(SucursalRequestDTO dto) {
        Sucursal nuevaSucursal = SucursalMapper.toEntity(dto);
        Sucursal guardada = sucursalRepo.save(nuevaSucursal);
        return SucursalMapper.toResponseDTO(guardada);
    }

    public void actualizarSucursal(Long id, SucursalRequestDTO dto) throws ApiException {
        Sucursal existente = sucursalRepo.findById(id)
                .filter(Sucursal::getActivo)
                .orElseThrow(() -> new ApiException(404, String.format("La sucursal con ID %d no existe", id)));

        existente.setNombreSucursal(dto.nombreSucursal());
        sucursalRepo.save(existente);
    }


    public void desactivarSucursal(Long id) throws ApiException {
        Sucursal existente = sucursalRepo.findById(id)
                .filter(Sucursal::getActivo)
                .orElseThrow(() -> new ApiException(404, String.format("La sucursal con ID %d no existe", id)));

        existente.setActivo(false);
        sucursalRepo.save(existente);
    }

    public List<SucursalResponseDTO> listarSucursales() throws ApiException {
        return sucursalRepo.findAll().stream()
                .filter(Sucursal::getActivo)
                .map(SucursalMapper::toResponseDTO)
                .toList();
    }

    // ==============================
    // Métodos para Familia
    // ==============================

    public FamiliaResponseDTO obtenerFamilia(Long id) throws ApiException {
        return familiaRepo.findById(id)
                .filter(Familia::getActivo)
                .map(FamiliaMapper::toResponseDTO)
                .orElseThrow(() -> new ApiException(404, String.format("La familia con ID %d no existe", id)));
    }

    public FamiliaResponseDTO crearFamilia(FamiliaRequestDTO dto) {
        Familia nuevaFamilia = FamiliaMapper.toEntity(dto);
        Familia guardada = familiaRepo.save(nuevaFamilia);
        return FamiliaMapper.toResponseDTO(guardada);
    }

    public void actualizarFamilia(Long id, FamiliaRequestDTO dto) throws ApiException {
        Familia existente = familiaRepo.findById(id)
                .filter(Familia::getActivo)
                .orElseThrow(() -> new ApiException(404, String.format("La familia con ID %d no existe", id)));

        existente.setNombreFamilia(dto.nombre_familia());
        existente.setDescripcion(dto.descripcion());
        familiaRepo.save(existente);
    }

    public void desactivarFamilia(Long id) throws ApiException {
        Familia existente = familiaRepo.findById(id)
                .filter(Familia::getActivo)
                .orElseThrow(() -> new ApiException(404, String.format("La familia con ID %d no existe", id)));

        existente.setActivo(false);
        familiaRepo.save(existente);
    }

    public List<FamiliaResponseDTO> listarFamilias() throws ApiException {
        return familiaRepo.findAll().stream()
                .filter(Familia::getActivo)
                .map(FamiliaMapper::toResponseDTO)
                .toList();
    }

    // ==============================
    // Métodos para SubFamilia
    // ==============================

    public SubFamiliaResponseDTO obtenerSubFamilia(Long id) throws ApiException {
        return subFamiliaRepo.findById(id)
                .filter(Subfamilia::getActivo)
                .map(SubFamiliaMapper::toResponseDTO)
                .orElseThrow(() -> new ApiException(404, String.format("La subfamilia con ID %d no existe", id)));
    }

    public SubFamiliaResponseDTO crearSubFamilia(SubFamiliaRequestDTO dto) throws ApiException {
        Subfamilia subfamilia = SubFamiliaMapper.toEntity(dto);
        Subfamilia guardada = subFamiliaRepo.save(subfamilia);
        return SubFamiliaMapper.toResponseDTO(guardada);
    }

    public void actualizarSubFamilia(Long id, SubFamiliaRequestDTO dto) throws ApiException {
        Subfamilia existente = subFamiliaRepo.findById(id)
                .filter(Subfamilia::getActivo)
                .orElseThrow(() -> new ApiException(404, String.format("La subfamilia con ID %d no existe", id)));

        existente.setNombreSubfamilia(dto.nombre_subfamilia());
        existente.setDescripcion(dto.descripcion());
        subFamiliaRepo.save(existente);
    }

    public void desactivarSubFamilia(Long id) throws ApiException {
        Subfamilia existente = subFamiliaRepo.findById(id)
                .filter(Subfamilia::getActivo)
                .orElseThrow(() -> new ApiException(404, String.format("La subfamilia con ID %d no existe", id)));

        existente.setActivo(false);
        subFamiliaRepo.save(existente);
    }

    public List<SubFamiliaResponseDTO> listarSubFamilias() throws ApiException {
        return subFamiliaRepo.findAll().stream()
                .filter(Subfamilia::getActivo)
                .map(SubFamiliaMapper::toResponseDTO)
                .toList();
    }

    // ==============================
    // Métodos para Producto
    // ==============================

    public ProductoResponseDTO obtenerProducto(Long id) throws ApiException {
        return productoRepo.findById(id)
                .filter(Producto::getActivo)
                .map(ProductoMapper::toProductoResponseDTO)
                .orElseThrow(() -> new ApiException(404, String.format("El producto con ID %d no existe", id)));
    }

    public ProductoResponseDTO crearProducto(ProductoRequestDTO dto) {
        Producto nuevoProducto = ProductoMapper.toEntity(dto);
        Producto guardado = productoRepo.save(nuevoProducto);
        return ProductoMapper.toProductoResponseDTO(guardado);
    }

    public void actualizarProducto(Long id, ProductoRequestDTO dto) throws ApiException {
        Producto existente = productoRepo.findById(id)
                .filter(Producto::getActivo)
                .orElseThrow(() -> new ApiException(404, String.format("El producto con ID %d no existe", id)));

        existente.setNombreProducto(dto.NombreProducto());
        existente.setCodigoProducto(dto.CodigoProducto());
        existente.setDescripcion(dto.Descripcion());
        existente.setPrecio(dto.Precio());

        productoRepo.save(existente);
    }

    public void desactivarProducto(Long id) throws ApiException {
        Producto existente = productoRepo.findById(id)
                .filter(Producto::getActivo)
                .orElseThrow(() -> new ApiException(404, String.format("El producto con ID %d no existe", id)));

        existente.setActivo(false);
        productoRepo.save(existente);
    }

    public List<ProductoResponseDTO> listarProductos() throws ApiException {
        return productoRepo.findAll().stream()
                .filter(Producto::getActivo)
                .map(ProductoMapper::toProductoResponseDTO)
                .toList();
    }
}

