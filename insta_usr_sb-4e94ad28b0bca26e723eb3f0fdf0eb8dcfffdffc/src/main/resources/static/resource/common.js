function isTouchDevice() {
  return (('ontouchstart' in window) ||
          (navigator.maxTouchPoints > 0) ||
          (navigator.msMaxTouchPoints > 0));
}

const $html = document.querySelector('html');
$html.classList.add(isTouchDevice() ? 'touch-posible' : 'touch-imposible');


const param__searchKeywordType = '${param.searchKeywordType}';
if(param__searchKeywordType.length > 0){
     $('.topnav from[name="searchKeywordType"]')
         .val('${param.searchKeywordType}');
}
