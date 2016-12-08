<!DOCTYPE HTML> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="base" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}"/>
<html>
<jsp:include page="_menu.jsp" />
<body>
	<jsp:include page="_saNavigation.jsp" />
 	<div id="main" class="container-fluid">
	   	<input type="hidden" value="${base}" name="base" id="base"/>	
		<div class="breadcrumbs">
			<ul>
				<li>
					<a href="home">Home</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listBrand">Brand</a>
				</li>
			</ul>
		</div>
        <div align="center">
			<h5>		
   				<a href="addBrand" class='btn'>
				<i class="fa fa-plus-circle"></i>New Brand</a>
			</h5>
			<div class="box-content nopadding">
				<table class="table table-hover table-nomargin table-bordered usertable">
				<thead>
					<tr class="thefilter">
						<th class="with-checkbox"></th>
					    <th>Name</th>
					    <th>Action</th>
					</tr>
					<tr>
						<th class="with-checkbox">
							<input type="checkbox" name="check_all" id="check_all">
						</th>
					    <th>Name</th>
					    <th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="brand" items="${listBrand}" varStatus="status">
						<tr>
							<td class="with-checkbox">
								<input type="checkbox" name="check" value="1">
							</td>
						    <td>${brand.brandname}</td>
							<td>
								<button class="btn btn-small" onclick="window.location='editBrand?brandid=${brand.brandid}';" >
							    	<i class="fa fa-edit"></i></button>
								<button class="btn btn-small" onclick="deleteBrand(${brand.brandid})">
									<i class="fa fa-trash-o"></i></button>
								<a href="listModel?brandid=${brand.brandid}">Model</a>				
						    </td>		                 
						</tr>
					</c:forEach>             
				</tbody>
				</table>
			</div>
		</div>
	</div>
	<script>
	$(document).ready(function() {
		if ($(".usertable").length > 0) {
			var l = {
	            sPaginationType: "full_numbers",
	            oLanguage: {
	                sSearch: "<span>Search:</span> ",
	                sInfo: "Showing <span>_START_</span> to <span>_END_</span> of <span>_TOTAL_</span> entries",
	                sLengthMenu: "_MENU_ <span>entries per page</span>"
	            },
	            sDom: "lfrtip",
	            aoColumnDefs: [{ bSortable: !1, aTargets: [0, 2]}]
			};
			
			l.sDom = "T" + l.sDom;
			l.oTableTools = { sSwfPath: "js/plugins/datatable/swf/copy_csv_xls_pdf.swf"};
			
	 		d = $(".usertable").dataTable(l);
	        $(".usertable").css("width", "100%");	
	        $(".dataTables_filter input").attr("placeholder", "Search here...");
	        $(".dataTables_length select").wrap("<div class='input-mini'></div>").chosen({ disable_search_threshold: 9999999 }),
	        $("#check_all").click(function (e) { $("input", d.fnGetNodes()).prop("checked", this.checked) });
	        $.datepicker.setDefaults({ dateFormat: "yy-mm-dd" });        
	        d.columnFilter({
	            sPlaceHolder: "head:after",
	            sRangeFormat: "{from}{to}",
	            aoColumns: [null, { type: "text" }, null]
	        });
		};		
	})
	
	function deleteBrand(brandid) {
		var base = $('#base').val();
    	if (window.location.protocol === 'https:') {
    	    base = base.replace("http", "https");
    	}	    	

    	jQuery.ajax({
            type: "DELETE",
            url: base+"/brand/delete/"+brandid,
            contentType: "application/json",
            data: "",
            dataType: "",
            success: function (data, status, jqXHR) {
				location.replace(location);
            },	        
            error: function (jqXHR, status) {
            }
        });	
    }
	</script>		
</body>
</html>