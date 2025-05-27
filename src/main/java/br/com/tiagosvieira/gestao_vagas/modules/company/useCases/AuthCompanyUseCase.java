package br.com.tiagosvieira.gestao_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.tiagosvieira.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.tiagosvieira.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyUseCase {

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;
  
  public void execute(AuthCompanyDTO authCompanyDTO) {
    var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(
      () -> {
        throw new UsernameNotFoundException("Company not found");
      }
    );

    // Verificar se a senha está correta
    var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());
    
    // Se a senha não corresponder -> erro
    if (!passwordMatches) {
      throw new UsernameNotFoundException("Invalid password");
    }

    // Se for igual -> Geran o token
  }
}
