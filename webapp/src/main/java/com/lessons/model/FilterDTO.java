package com.lessons.model;

import java.util.List;

public class FilterDTO {

   public Integer pageSize;

   public Integer offset;

   public List<FiltersListDTO> filters;

   public Integer getOffset() { return offset; }

   public void setOffset(Integer offset) { this.offset = offset; }

   public Integer getPageSize() {
      return pageSize;
   }

   public void setPageSize(Integer pageSize) {
      this.pageSize = pageSize;
   }

   public List<FiltersListDTO> getFilters() {
      return filters;
   }

   public void setFilters(List<FiltersListDTO> filters) {
      this.filters = filters;
   }
}
