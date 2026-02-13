package com.digis01.FCruzProgramacionNCapasWebSpring.ML;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

public class Usuario {
    
    private int idUsuario;
    
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$",message = "Solo acepto letras")
    @NotNull(message = "No puedo ser nulo")
    @NotEmpty(message = "No puedo ser vacio")
    @Size(min = 3, max = 50, message = "más de 2 letras min")
    private String nombre;
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+$",message = "Solo acepto letras")
    private String apellidoPaterno;
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$",message = "Solo acepto letras")
    private String apellidosMaterno;
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",message = "No se reconoce como un correo electronico")
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "La fecha no puede ser nula")
    @Past(message = "Es una fecha futura")
    private Date fechaNacimiento;
    @Pattern(regexp = "^[0-9]{10}$",message = "Solo números y no mas de 10 digitos")
    private String telefono;
    @Pattern(regexp = "^[0-9]{10}$",message = "Solo números y no mas de 10 digitos")
    private String celular;
    private String UserName;
    private String sexo;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&._-])[A-Za-z\\d@$!%*?&._-]{8,16}$",message = "No se acpeta como contraseña: solo 16 carcateres")
    private String password;
    @Pattern(regexp = "^[A-Z]{4}[0-9]{6}[HM][A-Z]{5}[0-9A-Z]{2}$",message = "CURP Invalido")
    private String CURP;
    @Valid
    public Rol Rol;
    @Valid
    public List<Direccion> Direccion;

    
    
    public Usuario() {
        this.Direccion = new ArrayList<>();
    }

    public Usuario(int idUsuario, String nombre, String apellidoPaterno, String apellidosMaterno, String email, Date fechaNacimiento, String telefono, String celular, String UserName, String sexo, String password, String CURP, Rol Rol, List<Direccion> Direccion) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidosMaterno = apellidosMaterno;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.celular = celular;
        this.UserName = UserName;
        this.sexo = sexo;
        this.password = password;
        this.CURP = CURP;
        this.Rol = Rol;
        this.Direccion = Direccion;
    }

    
    public int getIdUsuario(){
        return idUsuario;
    }
    
    public void setIdUsuario(int idUsuario){
        this.idUsuario = idUsuario;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getApellidoPaterno(){
        return apellidoPaterno;
    }
    
    public void setApellidoPaterno(String apellidoPaterno){
        this.apellidoPaterno = apellidoPaterno;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public Date getFechaNacimiento(){
        return fechaNacimiento;
    }
    
    public void setFechaNacimiento(Date fechaNacimiento){
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public String getApellidosMaterno(){
        return apellidosMaterno;
    }
    
    public void setApellidosMaterno (String apellidosMaterno){
        this.apellidosMaterno = apellidosMaterno;
    }
    
    public String getUserName (){
        return UserName;
    }
    
    public void setUserName (String UserName){
        this.UserName = UserName;
    }
    
    public String getTelefono () {
        return telefono;
    }
    
    public void setTelefono (String telefono){
        this.telefono = telefono;
    }
    
    public String getCelular (){
        return celular;
    }
    
    public void setCelular (String celular){
        this.celular = celular;
    }
    
    public String getSexo (){
        return sexo;
    }
    
    public void setSexo (String sexo){
        this.sexo = sexo;
    }
    
    public String getPassword (){
        return password;
    }
    
    public void setPassword (String password){
        this.password = password;
    }
    
    public String getCURP (){
        return CURP;
    }
    
    public void setCURP (String CURP){
        this.CURP = CURP;
    }

    public List<Direccion> getDireccion() {
        return Direccion;
    }

    public void setDireccion(List<Direccion> Direccion) {
        this.Direccion = Direccion;
    }

    public Rol getRol() {
        return Rol;
    }

    public void setRol (Rol Rol) {
        this.Rol = Rol;
    }
    
    
}
