$(document).ready(function () {

    var name = $('#name-input')
    var description = $('#description-input')
    var info = $('#info')

    info.change(function (event) {
        var checkbox = $(event.target);
        var text = checkbox.parent().find('span');
        var style = checkbox.prop('checked') ? 'line-through' : 'none';
        text.css('text-decoration', style);
    })

    info.click(function (event) {
        var elem = $(event.target);
        if (elem.is('button')) {
            elem.parent().remove();
        }
    })

    $("#add-button").click(function () {
        var nameValue = name.val();
        var descriptionValue = description.val();
        name.val('');
        description.val('');
        info.append(
            '<div><input type="checkbox"><span>' + nameValue +
            '</span><span>' + descriptionValue +'</span> <button id="delete-button">Delete</button></div>')
    })
})