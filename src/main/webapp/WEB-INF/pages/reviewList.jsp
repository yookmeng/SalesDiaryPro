<!DOCTYPE HTML>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="base" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}"/>
<html>
<jsp:include page="_menu.jsp" />
<body>
	<c:if test="${role == 'MD'}">
		<jsp:include page="_mdNavigation.jsp" />
	</c:if>
	<c:if test="${role == 'MA'}">
		<jsp:include page="_maNavigation.jsp" />
	</c:if>
	<c:if test="${role == 'TL'}">
		<jsp:include page="_tlNavigation.jsp" />
	</c:if>
	<c:if test="${role == 'USER'}">
		<jsp:include page="_userNavigation.jsp" />
	</c:if>
	<div id="main">
	   	<input type="hidden" value="${base}" name="base" id="base"/>
	   	<input type="hidden" value="${currentPeriod}" name="currentPeriod" id="currentPeriod"/>		   	
	   	<input type="hidden" value="${periods}" name="periods" id="periods"/>
		<div class="breadcrumbs">
			<ul>
				<li>
					<a href="home">Home</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listReview">Notes</a>
				</li>
			</ul>
		</div>
	    <div align="center">
			<div class="box box-color box-bordered">
				<div class="box-title">
					<h3>Review</h3>
				</div>
				<div class="box-content nopadding">
					<table class="table table-hover table-nomargin table-bordered usertable">
						<thead>
							<tr class="thefilter">
								<th class="with-checkbox"></th>
							    <th>Period</th>
							    <th>Date</th>
							    <th>Member</th>
							    <th>Team</th>
							    <th>Branch</th>
							    <th>Minute</th>	
							    <th>By</th>	
							    <th>Action</th>	
							</tr>
							<tr>
								<th class="with-checkbox">
									<input type="checkbox" name="check_all" id="check_all">
								</th>
							    <th>Period</th>
							    <th>Date</th>
							    <th>Member</th>
							    <th>Team</th>
							    <th>Branch</th>
							    <th>Minute</th>	
							    <th>By</th>	
							    <th>Action</th>	
							</tr>						
						</thead>
		            	<tbody>
			                <c:forEach var="review" items="${listReview}" varStatus="status">
			                <tr>
								<td class="with-checkbox">
									<input type="checkbox" name="check" value="1">
								</td>
							    <td>${review.period}</td>
							    <td>${review.reviewdate}</td>
			                    <td>${review.username}</td>
			                    <td>${review.teamname}</td>
			                    <td>${review.branchname}</td>
			                    <td>${review.minute}</td>
			                    <td>${review.reviewbyname}</td>
			                    <td>
									<button class="btn btn-small" onclick="window.location='editReview?reviewid=${review.reviewid}';">
								    	<i class="fa fa-edit"></i></button>
									<c:if test="${role != 'USER'}">
										<button class="btn btn-small" onclick="deleteReview(${review.reviewid})" >
											<i class="fa fa-trash-o"></i></button>
			                    	</c:if>
			                    </td>
			                </tr>
			                </c:forEach>             
		            	</tbody>
		            </table>
				</div>
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
	            aoColumnDefs: [{ bSortable: !1, aTargets: [0, 8]}]
			};
			
			l.sDom = "T" + l.sDom;
			l.oTableTools = { sSwfPath: "js/plugins/datatable/swf/copy_csv_xls_pdf.swf"};
	 		d = $(".usertable").dataTable(l);
	 		var allperiod = $("#periods").val();
	 		var arrayperiod = [];
	 		var data = "";
	 		allperiod = allperiod.replace("[","");
	 		allperiod = allperiod.replace("]","");
	 		allperiod = allperiod.replace(/ /g,"");
			while(allperiod.length>0){
				if (allperiod.search(",")>0){
					arrayperiod.push(allperiod.substring(0,allperiod.search(",")));
					allperiod = allperiod.substring(allperiod.search(",")+1,allperiod.length)
				}
				else{
					arrayperiod.push(allperiod);
					allperiod = "";
				}					
			}			
	        $(".usertable").css("width", "100%");	
	        $(".dataTables_filter input").attr("placeholder", "Search here...");
	        $(".dataTables_length select").wrap("<div class='input-mini'></div>").chosen({ disable_search_threshold: 9999999 }),
	        $("#check_all").click(function (e) { $("input", d.fnGetNodes()).prop("checked", this.checked) });
	        $.datepicker.setDefaults({dateFormat: "yy-mm-dd"});        
	        d.columnFilter({
	            sPlaceHolder: "head:after",
	            sRangeFormat: "{from}{to}",
	            aoColumns: [null, {type: "select", values: arrayperiod}, {type: "date-range" }, { type: "text" }, { type: "text" }, {type: "text" }, {type: "text" }, {type: "text" }, null]
	        });
		};
	});
	
    function deleteReview(reviewid) {
		var base = $('#base').val();
    	if (window.location.protocol === 'https:') {
    	    base = base.replace("http", "https");
    	}	    	

    	jQuery.ajax({
            type: "DELETE",
            url: base+"/review/delete/"+reviewid,
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