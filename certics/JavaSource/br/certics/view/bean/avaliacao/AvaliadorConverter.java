package br.certics.view.bean.avaliacao;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.certics.controller.facade.PessoaFisicaFacade;
import br.certics.model.entity.PessoaFisicaEntity;
import br.finf.control.facade.FacadeProvider;

@FacesConverter(value="avaliadorConverter", forClass=PessoaFisicaEntity.class) 
public class AvaliadorConverter implements Converter{

	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {  
        if(value != null) {  
        	PessoaFisicaFacade pessoaFisicaFacade = FacadeProvider.get().provide(PessoaFisicaFacade.class);
            return pessoaFisicaFacade.selectByID(Long.valueOf(value));  
        }
        return null;
    }
  
    public String getAsString(FacesContext context, UIComponent component, Object object) throws ConverterException {  
        if(object != null && object instanceof PessoaFisicaEntity) {  
            return ((PessoaFisicaEntity)object).getId() != null ? ((PessoaFisicaEntity)object).getId().toString() : "";  
        }
        return null;  
    }
    
}
