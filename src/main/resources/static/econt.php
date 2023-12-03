<?php

###################### JSON REST Example ######################

class EcontRestClient {
    public static function request($method, $params = array(),$timeout = 10) {
        //production endpoint
//         $endpoint = 'https://ee.econt.com/services';

        testing endpoint
        $endpoint = 'https://demo.econt.com/ee/services';

        // this is an example only, replace with proper credentials
        $auth = array(
            'login' => 'iasp-dev',
            'password' => 'iasp-dev',
        );

        $ch = curl_init();
        curl_setopt($ch, CURLOPT_URL, $endpoint . '/' . rtrim($method,'/'));
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
        curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
        curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, false);
        curl_setopt($ch, CURLOPT_HTTPHEADER, array("Content-type: application/json"));
        if(!empty($auth)) curl_setopt($ch, CURLOPT_USERPWD, $auth['login'].':'.$auth['password']);
        if(!empty($params)) curl_setopt($ch, CURLOPT_POSTFIELDS,json_encode($params));
        curl_setopt($ch, CURLOPT_TIMEOUT, !empty($timeout) && intval($timeout) ? $timeout : 4);
        $response = curl_exec($ch);
        $httpStatus = curl_getinfo($ch,CURLINFO_HTTP_CODE);
        curl_close($ch);

        $jsonResponse = json_decode($response,true);
        if(!$jsonResponse) {
            throw new \Exception("Invalid response.");
        }
        if(strpos($httpStatus,'2') !== 0) {
            throw new \Exception(self::flattenError($jsonResponse));//simple error handling by combining all the returned error's messages
        } else {
            return $jsonResponse;
        }
    }

    public static function flattenError($err) {
        $msg = trim($err['message']);
        $innerMsgs = array();
        foreach ($err['innerErrors'] as $e) $innerMsgs[] = self::flattenError($e);
        if (!empty($msg) && !empty($innerMsgs)) {
            $msg .= ": ";
        }
        return $msg . implode("; ", array_filter($innerMsgs));
    }
}

//get offices for city id 41 (Sofia)
var_dump(EcontRestClient::request("Nomenclatures/NomenclaturesService.getOffices.json",array('cityID' => 41)));

//validate a simple label
var_dump(EcontRestClient::request("Shipments/LabelService.createLabel.json",array(
    'mode' => 'validate',
    'label' => array(
        'senderClient' => array(
            'name' => 'Алъш-вериш ЕООД',
            'phones' => array('08888888888')
        ),
        'senderAgent' => array(
            'name' => 'Петър Петров',
            'phones' => array('08666666666')
        ),
        'senderOfficeCode' => '1000',
        'emailOnDelivery' => 'some_mail@example.org',
        'smsOnDelivery' => '08888888888',
        'receiverClient' => array(
            'name' => 'Иван Иванов',
            'phones' => array('08777777777')
        ),
        'receiverAddress' => array(
            'city' => array(
                'postCode' => '1000',
                'name' => 'София'
            ),
            'street' => 'васил левски',
            'num' => '51б'
        ),
        'packCount' => 1,
        'shipmentType' => 'pack',
        'weight' => '2',
        'sizeUnder60cm' => true,
        'shipmentDescription' => 'пратка с описание',
        'services' => array(
            'declaredValueAmount' => 78.42,
            'declaredValueCurrency' => 'BGN',
            'cdAmount' => 78.42,
            'cdCurrency' => 'BGN',
        ),
        'payAfterTest' => true,
    )
)));