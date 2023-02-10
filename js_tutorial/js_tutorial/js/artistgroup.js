
let companyPage = 0;
let keyword = "";
document.addEventListener("DOMContentLoaded", function(){
    elemId("imgfile").addEventListener("change", function(e) {
        if(e.target.files && e.target.files[0]) { //파일이 있다면, 
            const reader = new FileReader(); // 파일 리더 객체를 만들고
            reader.onload = function(e) { // 웹페이지 상에 파일로드가 완료되었다면
                elemId("preview").src = e.target.result; // 이미지 태그의 src 값으로 로드된 파일을 설정한다
            }
            reader.readAsDataURL(e.target.files[0]); // 설정된 파일 데이터를 읽어들인다.
        }
        else {
            elemId("preview").src= "";
        }
    })
    elemId("save").addEventListener("click", function() {
        const formData = new FormData();
        formData.append("name", elemId("name").value);
        formData.append("debutYear", elemId("debut").value);
        formData.append("company", elemId("company").value);
        formData.append("img", elemId("imgfile").files[0]);
        axios.put("http://localhost:8585/api/artist/group/insert", formData)
        .then(function(result) {
            // console.log(result);
            alert(result.data.message)
            elemId("name").value          = ""
            elemId("debut").value         = ""
            elemId("company").value       = ""
            elemId("imgfile").files[0]    = ""
            elemId("imgfile").src    = ""
            elemId("company").value    = ""
            elemId("company_name").innerHTML    = ""
        })
        .catch(function(err) {
            console.log(err);
        })
    })
    elemId("company_sel").addEventListener("click", function(){
        getCompanyList();
    })
})

function getCompanyList(page, keyword) {
    let url = baseURL+"/company/list";
    let params = {};
    if(page != undefined && page != null) {
        params.page = page;
    }
        if(keyword != undefined && keyword != null && keyword != "") {
            params.keyword = keyword;
        }
        axios.get(url, { params : params }).then(function(result) { 
            console.log(result) 
            const list = result.data.list;
            let tbodyContent = ""
            list.forEach((item) => {
                tbodyContent +=
                    '<tr>'+
                        '<td>'+item.pubSeq+'</td>'+
                        '<td>'+item.pubName+'</td>'+
                        '<td>'+
                            '<button onclick="selectCompany('+item.pubSeq+', \''+item.pubName+'\')">선택</button>'+
                    '</td>'+
                '</tr>';
            });
            let pagers = "";
            for(let i = 0; i < result.data.totalPage; i++) {
                // pagers += '<button onclick="getCompanyList('+i+', \''+elemId("search_keyword").value+'\')">' + (i+1)+ '</button>'
                pagers += `<button onclick="getCompanyList(${i},'${elemId("search_keyword").value}')">${i+1}</button>`
            }
            document.querySelector(".company_popup tbody").innerHTML = tbodyContent;
            document.querySelector(".popup_pager_area").innerHTML = pagers;
        })
        elemId("search_btn").addEventListener("click", function() {
            getCompanyList(0,elemId("search_keyword").value);
        })
    } 

    function selectCompany(seq, name) {
        elemId("company").value = seq;
        elemId("company_name").innerHTML = name;
    }
    // getCompanyList();
    function getArtistGroupList(page, keyword){
        if(page == null || page == undefined) page = 0;
        if(keyword == null || keyword == undefined) keyword = "";
        axios.get(baseURL+"/artist/group/list", { params : {page:page, keyword:keyword} })
        .then(function(result){
            console.log(result);
        })
    }
    getArtistGroupList();
