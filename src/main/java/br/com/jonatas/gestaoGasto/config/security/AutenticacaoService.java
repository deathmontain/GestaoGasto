package br.com.jonatas.gestaoGasto.config.security;

import br.com.jonatas.gestaoGasto.modelo.Cliente;
import br.com.jonatas.gestaoGasto.modelo.Sistema;
import br.com.jonatas.gestaoGasto.repository.SistemaRepository;
import br.com.jonatas.gestaoGasto.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private SistemaRepository sistemaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Sistema> sistema = sistemaRepository.findByEmail(username);

        if (sistema.isPresent()) {
            return sistema.get();
        }

        Optional<Cliente> usuario = clienteRepository.findByEmail(username);

        if (usuario.isPresent()) {
            return usuario.get();
        }

        throw new UsernameNotFoundException("Dados Inválidos");
    }
}