package Beans;

import java.util.List;

public class Data {
    private String numero;
    private String nombre_completo;
    private String nombres;
    private String apellido_paterno;
    private String apellido_materno;
    private String departamento;
    private String provincia;
    private String distrito;
    private String direccion;
    private String direccion_completa;
    private String ubigeo_reniec;
    private String ubigeo_sunat;
    private List<String> ubigeo;

    public Data(String numero, String nombre_completo, String nombres, String apellido_paterno, String apellido_materno, String departamento, String provincia, String distrito, String direccion, String direccion_completa, String ubigeo_reniec, String ubigeo_sunat, List<String> ubigeo) {
        this.numero = numero;
        this.nombre_completo = nombre_completo;
        this.nombres = nombres;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.departamento = departamento;
        this.provincia = provincia;
        this.distrito = distrito;
        this.direccion = direccion;
        this.direccion_completa = direccion_completa;
        this.ubigeo_reniec = ubigeo_reniec;
        this.ubigeo_sunat = ubigeo_sunat;
        this.ubigeo = ubigeo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccion_completa() {
        return direccion_completa;
    }

    public void setDireccion_completa(String direccion_completa) {
        this.direccion_completa = direccion_completa;
    }

    public String getUbigeo_reniec() {
        return ubigeo_reniec;
    }

    public void setUbigeo_reniec(String ubigeo_reniec) {
        this.ubigeo_reniec = ubigeo_reniec;
    }

    public String getUbigeo_sunat() {
        return ubigeo_sunat;
    }

    public void setUbigeo_sunat(String ubigeo_sunat) {
        this.ubigeo_sunat = ubigeo_sunat;
    }

    public List<String> getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(List<String> ubigeo) {
        this.ubigeo = ubigeo;
    }
}
