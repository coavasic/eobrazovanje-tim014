package eobrazovanje.tim014.controller;

import eobrazovanje.tim014.dto.LoginDTO;
import eobrazovanje.tim014.model.ChangePasswordData;
import eobrazovanje.tim014.model.Korisnik;
import eobrazovanje.tim014.repository.KorisnikRepo;
import eobrazovanje.tim014.security.TokenUtils;
import eobrazovanje.tim014.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.List;

@RestController
public class KorisnikController {

    @Autowired
    KorisnikService korisnikService;

    @Autowired
    KorisnikRepo korisnikRepo;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("api/korisnici")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<List<Korisnik>>(this.korisnikService.getAll(),HttpStatus.OK);
    }

    @PostMapping(value = "/api/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    loginDTO.getUsername(), loginDTO.getPassword());
            Authentication authentication = authenticationManager.authenticate(token);
            UserDetails details = userDetailsService.loadUserByUsername(loginDTO.getUsername());
            System.out.println(details.toString());
            return new ResponseEntity<String>(tokenUtils.generateToken(details),
                    HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<String>("Invalid login", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/api/lozinka")
    public ResponseEntity<?> changePass(@RequestBody ChangePasswordData changePasswordData){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Korisnik korisnik = korisnikRepo.findByKorisnickoIme(authentication.getName());
        if(bCryptPasswordEncoder.matches(changePasswordData.getOldPassword(),korisnik.getLozinka())){
            korisnik.setLozinka(bCryptPasswordEncoder.encode(changePasswordData.getNewPassword()));
            korisnikRepo.save(korisnik);
            return  new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
