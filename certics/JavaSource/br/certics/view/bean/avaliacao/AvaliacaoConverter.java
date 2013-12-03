package br.certics.view.bean.avaliacao;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.certics.controller.facade.PessoaFisicaFacade;
import br.certics.controller.facade.SoftwareFacade;
import br.certics.model.entity.AvaliacaoEntity;
import br.certics.model.entity.PessoaFisicaEntity;
import br.certics.model.entity.SoftwareEntity;
import br.finf.control.facade.FacadeProvider;

@FacesConverter(value="avaliacaoConverter", forClass=AvaliacaoEntity.class) 
public class AvaliacaoConverter {

	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {  
        if(value != null && component.equals("avaliador")) {  
        	PessoaFisicaFacade pessoaFisicaFacade = FacadeProvider.get().provide(PessoaFisicaFacade.class);
            return pessoaFisicaFacade.selectByID(Long.valueOf(value));  
        }else if(value != null && component.equals("software")){  
        	SoftwareFacade softwareFacade = FacadeProvider.get().provide(SoftwareFacade.class);
            return softwareFacade.selectByID(Long.valueOf(value));  
        }
        return null;
    }
  
    public String getAsString(FacesContext context, UIComponent component, Object object) throws ConverterException {  
        if(object != null && object instanceof PessoaFisicaEntity) {  
            return ((PessoaFisicaEntity)object).getId().toString();  
        }else if(object != null && object instanceof SoftwareEntity){
        	return ((SoftwareEntity)object).getId().toString();
        }
        return null;  
    }
    
}
