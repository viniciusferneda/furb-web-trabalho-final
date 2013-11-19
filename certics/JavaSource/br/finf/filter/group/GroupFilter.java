package br.finf.filter.group;

import java.util.ArrayList;
import java.util.List;

import br.finf.filter.SimpleFilter;
import br.finf.filter.simple.Filter;


public abstract class GroupFilter extends SimpleFilter {

	private final SimpleFilter[] filters;

	public GroupFilter(SimpleFilter... filters) {
		this.filters = filters;
	}

	@Override
	public String getExpression() {
		StringBuilder sb = new StringBuilder(" ( 1 = 1 ");
		
		for (SimpleFilter f : filters) {
			if (f.isValid()) {
				sb.append(" ");
				sb.append(f.getExpression());
				sb.append(" ");
			}
		}
		
		sb.append(" ) ");
		return sb.toString();
	}

	@Override
	public boolean isValid() {
		for (SimpleFilter f : filters) {
			if (f.isValid()) {
				return true;
			}
		}
		return false;
	}
	
	public List<Object> getParams() {
		List<Object> params = new ArrayList<Object>();
		
		for (SimpleFilter sf : filters) {
			if (sf instanceof Filter) {
				Filter f = (Filter) sf;
				if (f.isValidParam()) {
					params.add(f.getParam());
				}
			} else if (sf instanceof GroupFilter) {
				params.addAll(((GroupFilter) sf).getParams());
			}
		}
		return params;
	}

}
