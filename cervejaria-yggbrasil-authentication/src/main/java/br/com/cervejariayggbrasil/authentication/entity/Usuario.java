package br.com.cervejariayggbrasil.authentication.entity;


import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tb_usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "usuario", sequenceName = "sq_usuario", allocationSize = 1, initialValue = 1)
public class Usuario implements UserDetails{

    @Id
    @Column(name="id_usuario")
    @GeneratedValue(generator="usuario",strategy=GenerationType.SEQUENCE)
    private int id;

    private String login;

    private String senha;

    private PermissaoEnum permissao;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(permissao == PermissaoEnum.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
