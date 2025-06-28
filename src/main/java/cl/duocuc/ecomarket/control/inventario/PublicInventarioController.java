package cl.duocuc.ecomarket.control.inventario;

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

import cl.duocuc.ecomarket.servicio.ServicioInventario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/public/inventario")

public class PublicInventarioController {

    private final ServicioInventario service;

    public PublicInventarioController(ServicioInventario service){
        this.service = service;
    }


    @GetMapping("/{id}")
    public ResponseEntity<InventarioResponseDTO> consultarInventario(@PathVariable Long id){
        return ResponseEntity.ok(service.obtenerInventario(id));
    }

    @Operation(
            summary = "Crear nuevo inventario",
            description = "Permite registrar un nuevo inventario en el sistema con los datos proporcionados."
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos del inventario a registrar",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = InventarioRequestDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "201",
            description = "Inventario creado exitosamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = InventarioResponseDTO.class)
            )
    )
    @PostMapping
    public ResponseEntity<InventarioResponseDTO> crearInventario(@RequestBody InventarioRequestDTO inventario){
        InventarioResponseDTO creado = service.crearInventario(inventario);
        return ResponseEntity.status(201).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizarInventario(@PathVariable Long id, @RequestBody InventarioRequestDTO inventario) {
        service.actualizarInventario(id, inventario);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> desactivarInventario(@PathVariable Long id){
        service.desactivarInventario(id);
        return ResponseEntity.status(204).body(String.format("Inventario %d desactivado correctamente", id));
    }

    @GetMapping("/bodega/{id}")
    public ResponseEntity<BodegaResponseDTO> consultarBodega(@PathVariable Long id){
        return ResponseEntity.ok(service.obtenerBodega(id));
    }

    @Operation(
            summary = "Crear nueva bodega",
            description = "Permite registrar una nueva bodega en el sistema con los datos proporcionados."
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos de la bodega a registrar",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = BodegaRequestDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "201",
            description = "Bodega creada exitosamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = BodegaResponseDTO.class)
            )
    )
    @PostMapping("/bodega")
    public ResponseEntity<BodegaResponseDTO> crearBodega(@RequestBody BodegaRequestDTO bodega){
        BodegaResponseDTO creada = service.crearBodega(bodega);
        return ResponseEntity.status(201).body(creada);
    }

    @PutMapping("/bodega/{id}")
    public ResponseEntity<Void> actualizarBodega(@PathVariable Long id, @RequestBody BodegaRequestDTO bodega) {
        service.actualizarBodega(id, bodega);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/bodega/{id}")
    public ResponseEntity<String> desactivarBodega(@PathVariable Long id){
        service.desactivarBodega(id);
        return ResponseEntity.status(204).body(String.format("Bodega %d desactivada correctamente", id));
    }

    @GetMapping("/bodega")
    public ResponseEntity<List<BodegaResponseDTO>> listarBodegas(){
        return ResponseEntity.ok(service.listarBodegas());
    }

    @GetMapping("/sucursal/{id}")
    public ResponseEntity<SucursalResponseDTO> consultarSucursal(@PathVariable Long id){
        return ResponseEntity.ok(service.obtenerSucursal(id));
    }

    @Operation(
            summary = "Crear nueva sucursal",
            description = "Permite registrar una nueva sucursal en el sistema con los datos proporcionados."
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos de la sucursal a registrar",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = SucursalRequestDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "201",
            description = "Sucursal creada exitosamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = SucursalResponseDTO.class)
            )
    )
    @PostMapping("/sucursal")
    public ResponseEntity<SucursalResponseDTO> crearSucursal(@RequestBody SucursalRequestDTO sucursal){
        SucursalResponseDTO creada = service.crearSucursal(sucursal);
        return ResponseEntity.status(201).body(creada);
    }

    @PutMapping("/sucursal/{id}")
    public ResponseEntity<Void> actualizarSucursal(@PathVariable Long id, @RequestBody SucursalRequestDTO sucursal) {
        service.actualizarSucursal(id, sucursal);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/sucursal/{id}")
    public ResponseEntity<String> desactivarSucursal(@PathVariable Long id){
        service.desactivarSucursal(id);
        return ResponseEntity.status(204).body(String.format("Sucursal %d desactivada correctamente", id));
    }

    @GetMapping("/sucursal")
    public ResponseEntity<List<SucursalResponseDTO>> listarTodas(){
        return ResponseEntity.ok(service.listarSucursales());
    }

    @GetMapping("/familia/{id}")
    public ResponseEntity<FamiliaResponseDTO> consultarFamilia(@PathVariable Long id){
        return ResponseEntity.ok(service.obtenerFamilia(id));
    }

    @Operation(
            summary = "Crear nueva familia",
            description = "Permite registrar una nueva familia de productos en el sistema."
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos de la familia a registrar",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = FamiliaRequestDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "201",
            description = "Familia creada exitosamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = FamiliaResponseDTO.class)
            )
    )
    @PostMapping("/familia")
    public ResponseEntity<FamiliaResponseDTO> crearFamilia(@RequestBody FamiliaRequestDTO familia){
        FamiliaResponseDTO creada = service.crearFamilia(familia);
        return ResponseEntity.status(201).body(creada);
    }

    @PutMapping("/familia/{id}")
    public ResponseEntity<Void> actualizarFamilia(@PathVariable Long id, @RequestBody FamiliaRequestDTO familia) {
        service.actualizarFamilia(id, familia);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/familia/{id}")
    public ResponseEntity<String> desactivarFamilia(@PathVariable Long id){
        service.desactivarFamilia(id);
        return ResponseEntity.status(204).body(String.format("Familia %d desactivada correctamente", id));
    }

    @GetMapping("/familia")
    public ResponseEntity<List<FamiliaResponseDTO>> listarFamilias(){
        return ResponseEntity.ok(service.listarFamilias());
    }

    @GetMapping("/subfamilia/{id}")
    public ResponseEntity<SubFamiliaResponseDTO> consultarSubFamilia(@PathVariable Long id){
        return ResponseEntity.ok(service.obtenerSubFamilia(id));
    }

    @Operation(
            summary = "Crear nueva subfamilia",
            description = "Permite registrar una nueva subfamilia asociada a una familia existente."
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos de la subfamilia a registrar",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = SubFamiliaRequestDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "201",
            description = "Subfamilia creada exitosamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = SubFamiliaResponseDTO.class)
            )
    )
    @PostMapping("/subfamilia")
    public ResponseEntity<SubFamiliaResponseDTO> crearSubFamilia(@RequestBody SubFamiliaRequestDTO subfamilia){
        SubFamiliaResponseDTO creada = service.crearSubFamilia(subfamilia);
        return ResponseEntity.status(201).body(creada);
    }

    @PutMapping("/subfamilia/{id}")
    public ResponseEntity<Void> actualizarSubFamilia(@PathVariable Long id, @RequestBody SubFamiliaRequestDTO subfamilia) {
        service.actualizarSubFamilia(id, subfamilia);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/subfamilia/{id}")
    public ResponseEntity<String> desactivarSubFamilia(@PathVariable Long id){
        service.desactivarSubFamilia(id);
        return ResponseEntity.status(204).body(String.format("SubFamilia %d desactivada correctamente", id));
    }

    @GetMapping("/subfamilia")
    public ResponseEntity<List<SubFamiliaResponseDTO>> listarSubFamilias(){
        return ResponseEntity.ok(service.listarSubFamilias());
    }

    @GetMapping("/producto/{id}")
    public ResponseEntity<ProductoResponseDTO> consultarProducto(@PathVariable Long id){
        return ResponseEntity.ok(service.obtenerProducto(id));
    }

    @Operation(
            summary = "Crear nuevo producto",
            description = "Permite registrar un nuevo producto en el sistema."
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos del producto a registrar",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ProductoRequestDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "201",
            description = "Producto creado exitosamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ProductoResponseDTO.class)
            )
    )
    @PostMapping("/producto")
    public ResponseEntity<ProductoResponseDTO> crearProducto(@RequestBody ProductoRequestDTO producto){
        ProductoResponseDTO creado = service.crearProducto(producto);
        return ResponseEntity.status(201).body(creado);
    }

    @PutMapping("/producto/{id}")
    public ResponseEntity<Void> actualizarProducto(@PathVariable Long id, @RequestBody ProductoRequestDTO producto) {
        service.actualizarProducto(id, producto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/producto/{id}")
    public ResponseEntity<String> desactivarProducto(@PathVariable Long id){
        service.desactivarProducto(id);
        return ResponseEntity.status(204).body(String.format("Producto %d desactivado correctamente", id));
    }

    @GetMapping("/producto")
    public ResponseEntity<List<ProductoResponseDTO>> listarProductos(){
        return ResponseEntity.ok(service.listarProductos());
    }

}