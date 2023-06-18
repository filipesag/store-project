package br.com.beststore.service.validation;

import br.com.beststore.controller.exceptions.FieldMessage;
import br.com.beststore.domain.Cliente;
import br.com.beststore.domain.enums.TipoCliente;
import br.com.beststore.dto.NovoClienteDTO;
import br.com.beststore.repository.ClienteRepository;
import br.com.beststore.service.validation.utils.BR;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class ClientInsertValidator implements ConstraintValidator<ClientInsert, NovoClienteDTO> {

    @Override
    public void initialize(ClientInsert ann) {
    }

    @Autowired
    private ClienteRepository repository;

    @Override
    public boolean isValid(NovoClienteDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj", "CPF Inválido"));
        }

        if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCPNJ(objDto.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ Inválido"));
        }

        Cliente cliente = repository.findByEmail(objDto.getEmail());
        if (cliente != null){
            list.add(new FieldMessage("email", "Email já existente."));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}