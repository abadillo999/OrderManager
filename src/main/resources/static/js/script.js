$(document).ready(function () {

    var name = $('#name-input')
    var description = $('#description-input')
    var table = $('#table')

    table.change(function (event) {
        var checkbox = $(event.target);
        var text = checkbox.parent().find('span');
        var style = checkbox.prop('checked') ? 'line-through' : 'none';
        text.css('text-decoration', style);
    })

    table.click(function (event) {
        var elem = $(event.target);
        if (elem.is('button')) {
            elem.parent().parent().parent().remove();
        }
    })

    $("#add-button").click(function () {
        var nameValue = name.val();
        var descriptionValue = description.val();
        name.val('');
        description.val('');
        table.append(
            '<li class="list-group-item">\
                <div class="input-group">\
                    <div class="input-group-prepend">\
                        <div class="input-group-text">\
                            <input type="checkbox" aria-label="Checkbox for following text input">\
                        </div>\
                    </div>\
                    <span class="input-group-text">'+ nameValue + '</span><span class="input-group-text">'+ descriptionValue +'</span></div>\
                        <div class="input-group-append">\
                            <button class="btn btn-outline-secondary" type="button" id="delete-button">Delete</button>\
                        </div>\
                </div>\
            </li>')
            })
})