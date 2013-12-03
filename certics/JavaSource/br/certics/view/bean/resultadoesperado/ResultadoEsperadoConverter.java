package br.certics.view.bean.resultadoesperado;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.certics.controller.facade.AreaCompetenciaFacade;
import br.certics.model.entity.AreaCompetenciaEntity;
import br.certics.model.entity.ResultadoEsperadoEntity;
import br.finf.control.facade.FacadeProvider;

@FacesConverter(value="resultadoEsperadoConverter", forClass=ResultadoEsperadoEntity.class) 
public class ResultadoEsperadoConverter {

	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {  
        if(value != null) {  
        	AreaCompetenciaFacade areaCompetenciaFacade = FacadeProvider.get().provide(AreaCompetenciaFacade.class);
            return areaCompetenciaFacade.selectByID(Long.valueOf(value));  
        }  
        return null;  
    }
  
    public String getAsString(FacesContext context, UIComponent component, Object object) throws ConverterException {  
        if(object != null && object instanceof AreaCompetenciaEntity) {  
            return ((AreaCompetenciaEntity)object).getId().toString();  
        }  
        return null;  
    }
    
}
