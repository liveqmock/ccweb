/**
 * He he he
 * @param {type} xhr
 * @param {type} status
 * @param {type} args
 * @param {type} dialogName
 * @param {type} cbBooleanParam
 * @returns {undefined}
 */
function handleDialogSubmit(xhr, status, args, dialogName, cbBooleanParam) {
    var exp = true;
    if (cbBooleanParam) {
        exp = args[cbBooleanParam];
    }
    if (args.validationFailed || !exp) {
        PF(dialogName).jq.effect("shake", {times:5}, 100);
    } else {
        PF(dialogName).hide();
    }
}