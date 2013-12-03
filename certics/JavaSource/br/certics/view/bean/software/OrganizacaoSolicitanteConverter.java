package br.certics.view.bean.software;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.certics.controller.facade.OrganizacaoSolicitanteFacade;
import br.certics.model.entity.OrganizacaoSolicitanteEntity;
import br.finf.control.facade.FacadeProvider;

@FacesConverter(value="organizacaoSolicitanteConverter", forClass=OrganizacaoSolicitanteEntity.class)
public class OrganizacaoSolicitanteConverter implements Converter{

	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {  
        if(value != null) {  
        	OrganizacaoSolicitanteFacade organizacaoSolicitanteFacade = FacadeProvider.get().provide(OrganizacaoSolicitanteFacade.class);
            return organizacaoSolicitanteFacade.selectByID(Long.valueOf(value));  
        }  
        return null;  
    }
  
    public String getAsString(FacesContext context, UIComponent component, Object object) throws ConverterException {  
        if(object != null && object instanceof OrganizacaoSolicitanteEntity) {  
            return ((OrganizacaoSolicitanteEntity)object).getId() != null ? ((OrganizacaoSolicitanteEntity)object).getId().toString() : "";  
        }  
        return null;  
    }
    
}
