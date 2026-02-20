package com.digis01.FCruzProgramacionNCapasWebSpring.Controller;

import com.digis01.FCruzProgramacionNCapasWebSpring.DAO.ColoniaDAOImplementation;
import com.digis01.FCruzProgramacionNCapasWebSpring.DAO.EstadoDAOImplementation;
import com.digis01.FCruzProgramacionNCapasWebSpring.DAO.MunicipioDAOImplementation;
import com.digis01.FCruzProgramacionNCapasWebSpring.DAO.PaisDAOImplementation;
import com.digis01.FCruzProgramacionNCapasWebSpring.DAO.RolDAOImplementation;
import com.digis01.FCruzProgramacionNCapasWebSpring.DAO.UsuarioDAOImplementation;
import com.digis01.FCruzProgramacionNCapasWebSpring.ML.Colonia;
import com.digis01.FCruzProgramacionNCapasWebSpring.ML.Direccion;
import com.digis01.FCruzProgramacionNCapasWebSpring.ML.Estado;
import com.digis01.FCruzProgramacionNCapasWebSpring.ML.Municipio;
import com.digis01.FCruzProgramacionNCapasWebSpring.ML.Pais;
import com.digis01.FCruzProgramacionNCapasWebSpring.ML.Result;
import com.digis01.FCruzProgramacionNCapasWebSpring.ML.Rol;

import org.springframework.web.multipart.MultipartFile;
import com.digis01.FCruzProgramacionNCapasWebSpring.ML.Usuario;
import jakarta.validation.Valid;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Base64;

@Controller
@RequestMapping("Usuario")
public class UsuarioController {
    
    @Autowired
    private UsuarioDAOImplementation usuarioDAOImplementation;
    @Autowired
    private PaisDAOImplementation paisDAOImplementation;
    @Autowired
    private RolDAOImplementation rolDAOImplementation;
    @Autowired
    private EstadoDAOImplementation estadoDAOImplementation;
    @Autowired
    private MunicipioDAOImplementation municipioDAOImplementation;
    @Autowired
    private ColoniaDAOImplementation coloniaDAOImplementation;
    
    
    @GetMapping()          
    public String index (Model model){
        Result result = usuarioDAOImplementation.GetAll();
        model.addAttribute("usuarios", result.objects);

        Result resultRoles = rolDAOImplementation.GetAll();  
        model.addAttribute("roles", resultRoles.objects);

        model.addAttribute("paises", paisDAOImplementation.GetAll().objects);
        return "GetAll";
    } 
    
    @PostMapping("/update")
    public String Update(@ModelAttribute Usuario usuario, RedirectAttributes redirectAttributes) {      

        Result result = usuarioDAOImplementation.Update(usuario);

        if (result.correct) {
            redirectAttributes.addFlashAttribute("success", "Usuario actualizado correctamente");
        } else {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar el usuario");
        }

        return "redirect:/Usuario";
    }


    
    @GetMapping("form")
    public String Accion(Model model) {
        

        Usuario usuario = new Usuario();

        usuario.setDireccion(new ArrayList<>());
        Direccion direccion = new Direccion();

        Pais pais = new Pais();
        Estado estado = new Estado();
        estado.setPais(pais);
        Municipio municipio = new Municipio();
        municipio.setEstado(estado);
        Colonia colonia = new Colonia();
        colonia.setMunicipio(municipio);

        direccion.setColonia(colonia);

        usuario.getDireccion().add(direccion);

        model.addAttribute("usuario", usuario);

        Result resultPaises = paisDAOImplementation.GetAll();
        model.addAttribute("paises", resultPaises.objects);

        Result resultRoles = rolDAOImplementation.GetAll();
        model.addAttribute("roles", resultRoles.objects);

        return "formulario"; 
    }
    
   @PostMapping("form")
    public String Accion(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult bindingResult, @RequestParam(value = "archivoFoto", required = false) MultipartFile foto, Model model) {


        if (foto != null && !foto.isEmpty()) {
            String tipo = foto.getContentType();

            if ("image/jpeg".equals(tipo) || "image/png".equals(tipo)) {
                try {
                    byte[] bytes = foto.getBytes();
                    String base64 = Base64.getEncoder().encodeToString(bytes);
                    String imagenFinal = "data:" + tipo + ";base64," + base64;
                    usuario.setFoto(imagenFinal);
                } catch (Exception e) {
                    e.printStackTrace();
                    bindingResult.rejectValue("foto", "error.usuario", "Error al procesar la imagen");
                }
            } else {
                bindingResult.rejectValue("foto", "error.usuario", "Solo se permiten imágenes JPG o PNG");
            }
        }

        if (bindingResult.hasErrors()) {

            model.addAttribute("paises", paisDAOImplementation.GetAll().objects);
            model.addAttribute("roles", rolDAOImplementation.GetAll().objects);


            model.addAttribute("abrirModalUsuario", true); 
            model.addAttribute("error", "Corrige los errores del formulario");


            if (usuario.getDireccion() == null || usuario.getDireccion().isEmpty()) {
                Direccion direccion = new Direccion();
                Colonia colonia = new Colonia();
                Municipio municipio = new Municipio();
                Estado estado = new Estado();
                Pais pais = new Pais();
                estado.setPais(pais);
                municipio.setEstado(estado);
                colonia.setMunicipio(municipio);
                direccion.setColonia(colonia);
                usuario.setDireccion(new ArrayList<>());
                usuario.getDireccion().add(direccion);
            }

            model.addAttribute("usuario", usuario); 
            return "formulario";
        }

        Result result = usuarioDAOImplementation.Add(usuario);

        if (result.correct) {
            model.addAttribute("success", "Usuario agregado correctamente");
            return "redirect:/Usuario";
        } else {

            model.addAttribute("error", "Error al agregar el usuario");

            model.addAttribute("paises", paisDAOImplementation.GetAll().objects);
            model.addAttribute("roles", rolDAOImplementation.GetAll().objects);
            model.addAttribute("usuario", usuario);
            return "formulario";    
        }

    }





    
    @GetMapping("Formulario")
    public String formUsuaFormulariorio(
            @RequestParam(required = false) Integer id,
            Model model) {
        
        Usuario usuario = new Usuario();

        model.addAttribute("paises", paisDAOImplementation.GetAll().objects);
        model.addAttribute("roles", rolDAOImplementation.GetAll().objects);

        if (id != null) {
            Result result = usuarioDAOImplementation.GetAll();
            if (result.objects != null) {
                for (Object obj : result.objects) {
                    Usuario u = (Usuario) obj;
                    if (u.getIdUsuario() == id) {
                        usuario = u;
                        break;
                    }
                }
            }
        }

        model.addAttribute("usuario", usuario);
        return "formulario";
    }

    
    @GetMapping("getEstadosByPais/{IdPais}")
    @ResponseBody
    public Result getEstadosByPais(@PathVariable("IdPais") int IdPais){
        
        Result result = estadoDAOImplementation.getEstadosByPais(IdPais);
                
        return result;
    }
    
    @GetMapping("getMunicipioByEstado/{IdEstado}")
    @ResponseBody 
    public Result getMunicipioByEstado(@PathVariable("IdEstado") int IdEstado){
        
        Result result = municipioDAOImplementation.getMunicipioByEstado(IdEstado);

        return result;
    }
    
    @GetMapping("getColoniaByMunicipios/{IdMunicipio}")
    @ResponseBody 
    public Result getColoniaByMunicipios(@PathVariable("IdMunicipio") int IdMunicipio){
        
        Result result = coloniaDAOImplementation.getColoniaByMunicipios(IdMunicipio);

        return result;
    }
    
    @GetMapping("getDireccionByCP/{CodigoPostal}")
    @ResponseBody
    public Result getDireccionByCP(@PathVariable String CodigoPostal) {
        
        Result result = coloniaDAOImplementation.getDireccionByCP(CodigoPostal);
        
        return result;
    }

}
