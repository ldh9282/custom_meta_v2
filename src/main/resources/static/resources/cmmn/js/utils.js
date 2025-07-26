/**
 * @object alertUtils
 * @desc 알럿유틸
 */
const alertUtils = {
    /**
     * @function checkRequiredFields
     * @desc 필수값 체크
     */
    checkRequiredFields: function () {
        var fieldList = [];
        var missingFields = [];

        $("[data-required]").each(function () {
            var fieldLabel = $("label[for=" + $(this).attr("id") + "]").text();
            var fieldValue = $(this).val();
            var fieldObj = { label: fieldLabel, value: fieldValue };
            fieldList.push(fieldObj);
        });

        for (var idx in fieldList) {
            if (!fieldList[idx].value) {
                missingFields.push(fieldList[idx]);
            }
        }

        if (missingFields.length > 0) {
            var message = "다음 필수값을 입력해주세요:\n\n";
            missingFields.forEach(function (field) {
                message += "- " + field.label + "\n";
            });
            alertUtils.showAlert(message);

            return false;
        }

        return true;
    },

    /**
     * @function showAlert
     * @desc 알럿
     */
    showAlert: function (message, callback) {
        var alertModal = $("#alertModal");

        if (!alertModal) {
            alert(message);
        } else {
            $("#modal-body1").html(message.replace(/\n/g, "<br>"));
            var modal = new bootstrap.Modal(alertModal);
            alertModal.removeAttr("inert");
            modal.show();
            alertModal.off("hide.bs.modal").on("hide.bs.modal", function () {
                $("body").focus();
                alertModal.attr("inert", "true");
                if (callback) {
                    callback();
                }
            });

            setTimeout(() => {
                modal.hide();
            }, 3000);
        }
    },

    /**
     * @function showConfirm
     * @desc 확인창
     */
    showConfirm: function (message, callback) {
        var alertModal = $("#alertModal2");

        if (!alertModal) {
            alert(message);
        } else {
            $("#modal-body2").html(message.replace(/\n/g, "<br>"));
            var modal = new bootstrap.Modal(alertModal);
            alertModal.removeAttr("inert");
            modal.show();
            alertModal.off("hide.bs.modal").on("hide.bs.modal", function () {
                $("body").focus();
                alertModal.attr("inert", "true");
            });
            // 확인 버튼시 콜백
            $("#btnConfirmAlertModal2")
                .off("click")
                .one("click", function () {
                    if (callback) {
                        callback();
                    }
                });
        }
    },
};

/**
 * @object dateUtils
 * @desc date 유틸
 */
const dateUtils = {
	/**
	 * @function format
	 * @desc date 를 string 혹은 object 로 format
	 */
	format: function(date, format, isObject) {
		
	    if (!date || !(date instanceof Date)) {
	        return isObject ? { year: '', month: '', day: '', hour: '', minute: '', second: '' } : "";
	    }
	
	    const options = {hourCycle: 'h23'};
	    
	    const formatMapping = {
	        'yyyy': 'numeric',
	        'MM': '2-digit',
	        'dd': '2-digit',
	        'HH': '2-digit',
	        'mm': '2-digit',
	        'ss': '2-digit'
	    };
	
	    for (const key in formatMapping) {
	        if (format.includes(key)) {
	            const optionKey = key[0] === 'y' ? 'year' :
	                              key[0] === 'M' ? 'month' :
	                              key[0] === 'd' ? 'day' :
	                              key[0] === 'H' ? 'hour' :
	                              key[0] === 'm' ? 'minute' :
	                              key[0] === 's' ? 'second' : '';
	            options[optionKey] = formatMapping[key];
	        }
	    }
	
	    const formatter = new Intl.DateTimeFormat('ko-KR', options);
	    const parts = formatter.formatToParts(date);
	    let formattedDate = format;
	    let result = {}
	
	    parts.forEach(({ type, value }) => {
	        const key = type === 'year' ? 'yyyy' :
	                    type === 'month' ? 'MM' :
	                    type === 'day' ? 'dd' :
	                    type === 'hour' ? 'HH' :
	                    type === 'minute' ? 'mm' :
	                    type === 'second' ? 'ss' : '';
	        if (key) {
	            formattedDate = formattedDate.replace(key, value);
	            if (isObject) {
	                result[type] = value;
	            }
	        }
	    });
	
	    return isObject ? result : formattedDate;
	}
};

/**
 * @object timerUtils
 * @desc 타이머 유틸
 */
const timerUtils = {
	
	/**
	 * @function getDisplayTime
	 * @desc ms 를 hour, min, sec 을 가지는 object 로 반환
	 */
	getDisplayTime: function(diffMillis) {
		let result = {};
		
		if (diffMillis <= 0) {
			result = {
				hour: '00'
				, min: '00'
				, sec: '00'
			};
			
			return result;
		}
		
		let diffSeconds = Math.floor(diffMillis / 1000);
		let diffMinutes = Math.floor(diffSeconds / 60);
		let diffHours = Math.floor(diffMinutes / 60);
		let diffDays = Math.floor(diffHours / 24);
		
		let displaySeconds = (diffSeconds % 60).toString().padStart(2, '0');
		let displayMinutes = (diffMinutes % 60).toString().padStart(2, '0');
		let displayHours = (diffHours).toString().padStart(2, '0');
		
		result = {
			hour: displayHours
			, min: displayMinutes
			, sec: displaySeconds
		};
		
		return result;
	}
}

/**
 * @function camel2Snake
 * @desc 카멜명을 스네이크명으로 변환
 */
function camel2Snake(str) {
    str = str.charAt(0).toLowerCase() + str.substr(1, str.length);
    return str.replace(/([A-Z])/g, (word) => '_' + word.toLowerCase()).toUpperCase();
}

/**
 * @function snake2Camel
 * @desc 스네이크명을 카멜명으로 변환
 */
function snake2Camel(str) {
    str = str.toLowerCase();
    return str.replace(/_./g, (word) => word.charAt(1).toUpperCase());
}

/**
 * @function changeCase
 * @desc 대소문자 전환
 */
function changeCase(str) {
    if (str === str.toLowerCase()) {
        return str.toUpperCase();
    } else if (str === str.toUpperCase()) {
        return str.toLowerCase();
    } else {
        return str;
    }
}

/**
 * @function nanoid
 * @desc 랜덤 id 반환
 */
function nanoid(size = 21) {
 	const alphabet = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-';
  	let id = '';
  	for (let i = 0; i < size; i++) {
    	id += alphabet[Math.floor(Math.random() * alphabet.length)];
  	}
  	return id;
}