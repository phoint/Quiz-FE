$(document).ready(function () {
  const date = new Date();
  date.setSeconds(date.getSeconds() + 10);

  var stickySidebar = $.fn.stickySidebar.noConflict(); // Returns $.fn.stickySidebar assigned value.
  $.fn.stickySidebar = stickySidebar;

  $('#box-siderbar').stickySidebar({
    containerSelector: '#main-container',
    innerWrapperSelector: '.sidebar__inner',
    topSpacing: 150,
    bottomSpacing: 0
  });
  // 				$('#getting-started').countdown(date, function(event) {
  // 					$(this).html(event.strftime('%H:%M:%S'));
  // 				}).on('finish.countdown', function() {
  // 					alert('finish');
  // 				});

  $('#main-container').paginga({
    itemsPerPage: 1,
    itemsContainer: '.items',
    item: '> div',
    page: 1,
    nextPage: ".nextPage",
    previousPage: ".previousPage",
    pageNumbers: ".pageNumbers",
    scrollToTop: {
      offset: 100,
      speed: 100,
    },
  });
});