class EcontRestClient {
    static async request(method, params = {}, timeout = 10) {
        // Production endpoint
        // const endpoint = 'https://ee.econt.com/services';

        // Testing endpoint
        const endpoint = 'https://demo.econt.com/ee/services';

        // This is an example only, replace with proper credentials
        const auth = {
            login: 'iasp-dev',
            password: '1Asp-dev',
        };

        const url = `${endpoint}/${method}`;

        const headers = {
            'Content-Type': 'application/json',
        };

        if (!isNaN(timeout)) {
            timeout *= 1000; // convert seconds to milliseconds
        } else {
            timeout = 4000; // default timeout 4 seconds
        }

        const options = {
            method: 'POST',
            headers,
            body: JSON.stringify(params),
            timeout,
        };

        if (auth) {
            headers['Authorization'] = `Basic ${btoa(`${auth.login}:${auth.password}`)}`;
        }

        try {
            const response = await fetch(url, options);
            const jsonResponse = await response.json();

            if (!response.ok) {
                throw new Error(EcontRestClient.flattenError(jsonResponse));
            }

            return jsonResponse;
        } catch (error) {
            throw new Error(`Invalid response: ${error.message}`);
        }
    }

    static flattenError(err) {
        let msg = err.message.trim();
        const innerMsgs = err.innerErrors.map((e) => EcontRestClient.flattenError(e));

        if (msg && innerMsgs.length > 0) {
            msg += ': ';
        }

        return msg + innerMsgs.filter(Boolean).join('; ');
    }
}

// Get offices for city id 41 (Sofia)
EcontRestClient.request("Nomenclatures/NomenclaturesService.getOffices.json", { cityID: 7 })
    .then((result) => console.log(result))
    .catch((error) => console.error(error));

// Validate a simple label
EcontRestClient.request("Shipments/LabelService.createLabel.json", {
    mode: 'validate',
    label: {
        senderClient: {
            name: 'Алъш-вериш ЕООД',
            phones: ['08888888888'],
        },
        senderAgent: {
            name: 'Петър Петров',
            phones: ['08666666666'],
        },
        senderOfficeCode: '9018',
        emailOnDelivery: 'some_mail@example.org',
        smsOnDelivery: '08888888888',
        receiverClient: {
            name: 'Иван Иванов',
            phones: ['08777777777'],
        },
        receiverAddress: {
            city: {
                postCode: '8501',
                name: 'Айтос',
            },
            street: 'Георги Кондолов',
            num: '7',
        },
        packCount: 1,
        shipmentType: 'pack',
        weight: '2',
        sizeUnder60cm: true,
        shipmentDescription: 'пратка с описание',
        services: {
            declaredValueAmount: 78.42,
            declaredValueCurrency: 'BGN',
            cdAmount: 78.42,
            cdCurrency: 'BGN',
        },
        payAfterTest: true,
    },
})
    .then((result) => console.log(result))
    .catch((error) => console.error(error));