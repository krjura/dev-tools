export const translateEn = {
  alerts: {
    serverError: 'Application Error',
    backend: {
      constraints: {
        class: {
          BcryptDataConstraint: 'data should be between 0 and 100 characters',
          BcryptIterationConstraint: 'Iterations should be a number between 4 and 15'
        }
      }
    }
  },
  pages: {
    passwordGenerator: {
      pageHeader: "Password Generator",
      panelHeading: "Password Configuration",
      form: {
        config: {
          title: "Characters",
          useCapitalLetters: "Use capital letters (A, B, C, ...)",
          useSmallLetters: "Use small letters (a, b, c, ...)",
          useNumbers: "Use numbers (0, 1, 2, 3, ...)",
          length: 'Length of password',
          submit: 'Generate'
        }
      },
      results: {
        panelHeading: "Generated Passwords",
        copied: "Copied",
        copyToClipboard: "Copy to clipboard",
        noData: "No passwords are generated at this point"
      }
    },
    base64Decoder: {
      pageHeader: 'Base64 Decoder',
      pageHeading: 'Decoding data',
      form: {
        data: 'Data',
        submit: 'Decode'
      }
    },
    base64Encoder: {
      pageTitle: 'Base64 Encoder',
      pageHeading: 'Encoding Data',
      form: {
        submit: 'Encode'
      },
      result: {
        title: 'Encoding Result',
        data: 'Data',
        downloadData: 'Download data',
        value: 'Value',
        downloadResult: 'Download result'
      },
      clipboard: {
        copy: 'Copy to clipboard',
        copied: 'Copied'
      }
    },
    bcryptPassword: {
      pageHeader: 'BCrypt Password Encoder',
      panelHeading: 'Encoding Data',
      form: {
        iterations: 'Iterations',
        iterationsHelpBlock: 'number of iterations',
        data: 'Data',
        dataHelpBlock: 'Data to encode',
        submit: 'Encode'
      },
      result: {
        title: 'Result',
        password: 'Password:',
        iterations: 'Iterations:',
        encodedData: 'Encoded data',
        copied: 'Copied',
        copyToClipboard: 'Copy to clipboard'
      }
    }
  }
};
