$(document).ready(function () {
  /** Summernote Editor */
  $('.textarea').summernote();
  /** Tool Tip */
  $('[data-toggle="tooltip"]').tooltip();

  /** Transfer value for Create Question form in Bootstrap Modal */
  $('#createQuestion').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget) // Button that triggered the modal
    var sectionId = button.data('whatever') // Extract info from data-* attributes
    // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
    // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
    var modal = $(this)
    modal.find('.modal-title').text('Create new question in Section ' + sectionId)
    modal.find('.modal-body #section-id').val(sectionId)
  });
});