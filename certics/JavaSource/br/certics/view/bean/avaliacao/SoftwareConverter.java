package br.certics.view.bean.avaliacao;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.certics.controller.facade.SoftwareFacade;
import br.certics.model.entity.SoftwareEntity;
import br.finf.control.facade.FacadeProvider;

@FacesConverter(value="softwareConverter", forClass=SoftwareEntity.class) 
public class SoftwareConverter implements Converter{

	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {  
        if(value != null){  
        	SoftwareFacade softwareFacade = FacadeProvider.get().provide(SoftwareFacade.class);
            return softwareFacade.selectByID(Long.valueOf(value));  
        }
        return null;
    }
  
    public String getAsString(FacesContext context, UIComponent component, Object object) throws ConverterException {  
        if(object != null && object instanceof SoftwareEntity){
        	return ((SoftwareEntity)object).getId() != null ? ((SoftwareEntity)object).getId().toString() : "";
        }
        return null;  
    }
    
}
